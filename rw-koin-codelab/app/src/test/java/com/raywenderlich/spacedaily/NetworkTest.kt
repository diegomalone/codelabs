package com.raywenderlich.spacedaily

import com.raywenderlich.spacedaily.di.NamedConstants
import com.raywenderlich.spacedaily.di.networkModule
import com.raywenderlich.spacedaily.network.NASAAPIInterface
import com.squareup.moshi.Moshi
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit

class NetworkTest : KoinTest {
    private val api: NASAAPIInterface by inject()
    private val moshi: Moshi by inject()
    private val retrofit: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()
    private val baseUrl: String by lazy { get<String>(named(NamedConstants.BASE_URL)) }

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test retrofit created`() {
        assertNotNull(retrofit)
        assertEquals("https://api.nasa.gov/planetary/", baseUrl)
    }

    @Test
    fun `test moshi created`() {
        assertNotNull(moshi)
    }

    @Test
    fun `test API created`() {
        assertNotNull(api)
    }

    @Test
    fun `test OkHttpClient`() {
        assertNotNull(okHttpClient)
        assertEquals(30_000, okHttpClient.connectTimeoutMillis)
        assertEquals(30_000, okHttpClient.writeTimeoutMillis)
        assertEquals(30_000, okHttpClient.readTimeoutMillis)
        assertEquals(1, okHttpClient.interceptors.size)
    }
}