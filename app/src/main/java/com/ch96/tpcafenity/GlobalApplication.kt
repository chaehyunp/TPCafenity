package com.ch96.tpcafenity

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Kakao SDK 초기화 [네이티브 앱키]
        KakaoSdk.init(this, "a83c62a502c39058961b5d0b74fac32f")
    }
}