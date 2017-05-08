package co.infinum.ultimate_drives.ui

import co.infinum.mvpexample.data.managers.MoshiProvider
import com.squareup.moshi.Moshi
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.stubbing.OngoingStubbing
import java.io.InputStreamReader

open class BaseTest {

    enum class Response {
        SUCCESS, FAILURE
    }

    @get:Rule
    var mockitoJUnitRule = MockitoJUnit.rule()

    fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)!!

    val moshi: Moshi = MoshiProvider.getInstance().moshi

    fun getFileAsString(filename: String): String {
        val fileStream = javaClass.classLoader.getResourceAsStream(filename)
        val fileReader: InputStreamReader? = fileStream.reader()
        return fileReader?.readText() ?: ""
    }
}