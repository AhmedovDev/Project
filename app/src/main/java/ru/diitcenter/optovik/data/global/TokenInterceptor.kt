package ru.diitcenter.optovik.data.global

import ru.diitcenter.optovik.data.prefs.PrefsHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(
    private val prefsHelper: PrefsHelper
) : okhttp3.Interceptor {
    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {

        var request = chain.request()
        /*
         * Добавляет header с токеном, только если нет другого Authorization (Basic)
         *
         * Возможна ситуация, когда токен сохранен, но юзер еще не зареган.
         * Например, юзер ввел корректный СМС-код, после чего сохраняется токен
         * и выполняется переход на экран регистрации юзера, но при этом регистрацию не прошел и вышел.
         */
        if (request.headers()["Authorization"] == null) {
            prefsHelper.getToken()?.let {
                request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $it")
                    .build()
            }
        }
        return chain.proceed(request)    }
}