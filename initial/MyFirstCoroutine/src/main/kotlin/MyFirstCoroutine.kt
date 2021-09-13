import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() {


    logMessage("Pre>>>")
    logHelloWorld()
    logMessage("<<<Post")


    Thread.sleep(5000)

}


private fun logHelloWorld() {
    helloMessage()
    worldMessage()
}

private fun helloMessage() {
    val myPrime = BigInteger.probablePrime(4096, Random())
    logMessage("Hello $myPrime")
}


private fun worldMessage() {
    val myPrime = BigInteger.probablePrime(4096, Random())
    logMessage("World!! $myPrime")
}


fun logMessage(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage("id: $id ${it.key} = ${it}") }
}