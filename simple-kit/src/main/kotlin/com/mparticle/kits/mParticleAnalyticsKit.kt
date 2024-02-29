package com.mparticle.kits

import android.content.Context
import com.mparticle.MPEvent
import com.mparticle.MParticle

/**
 *
 * This is an mParticle kit, used to extend the functionality of mParticle SDK. Most Kits are wrappers/adapters
 * to a 3rd party SDK, primarily used to map analogous public mParticle APIs onto a 3rd-party API/platform.
 *
 *
 * Follow the steps below to implement your kit:
 *
 * - Edit ./build.gradle to add any necessary dependencies, such as your company's SDK
 * - Rename this file/class, using your company name as the prefix, ie "AcmeKit"
 * - View the javadocs to learn more about the KitIntegration class as well as the interfaces it defines.
 * - Choose the additional interfaces that you need and have this class implement them,
 * ie 'AcmeKit extends KitIntegration implements KitIntegration.PushListener'
 *
 * In addition to this file, you also will need to edit:
 * - ./build.gradle (as explained above)
 * - ./README.md
 * - ./src/main/AndroidManifest.xml
 * - ./consumer-proguard.pro
 */
class mParticleAnalyticsKit : KitIntegration() {
    private val MP_ANALYTICS_EVENT_NAME = "Registration Submit"

    public override fun onKitCreate(
        settings: MutableMap<String, String>?,
        context: Context?
    ): MutableList<ReportingMessage>? {
        /** TODO: Initialize your SDK here
         * This method is analogous to Application#onCreate, and will be called once per app execution.
         *
         * If for some reason you can't start your SDK (such as settings are not present), you *must* throw an Exception
         *
         * If you forward any events on startup that are analagous to any mParticle messages types, return them here
         * as ReportingMessage objects. Otherwise, return an null.
         */

        return null
    }

    override fun logEvent(baseEvent: MPEvent): MutableList<ReportingMessage>? {
        if (baseEvent.eventName.equals(MP_ANALYTICS_EVENT_NAME, true)) {
            val customAttributes: MutableMap<String, String> = HashMap()
            customAttributes["anonymous_mpid"] = MParticle.getInstance()?.Identity()?.users?.get(1)?.id.toString()
            customAttributes["known_mpid"] = MParticle.getInstance()?.Identity()?.users?.get(0)?.id.toString()
            val event = MPEvent.Builder("User Alias", MParticle.EventType.Other)
                .customAttributes(customAttributes)
                .build()
            MParticle.getInstance()?.logEvent(event);
        }
        return super.logEvent(baseEvent)
    }

    override fun getName(): String {
        //TODO: Replace this with your company name
        return "mParticleAnalytics"
    }


    override fun setOptOut(optedOut: Boolean): MutableList<ReportingMessage>? {
        //TODO: Disable or enable your SDK when a user opts out.
        //TODO: If your SDK can not be opted out of, return null
        val optOutMessage = ReportingMessage(
            this,
            ReportingMessage.MessageType.OPT_OUT,
            System.currentTimeMillis(),
            null
        )
        return null
    }
}