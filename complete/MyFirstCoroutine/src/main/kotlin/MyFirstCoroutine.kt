import kotlinx.coroutines.*
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {

    //val scope = CoroutineScope(Dispatchers.Default)
    launch {
        logMessage("Pre>>>")
        logHelloWorld()
        logMessage("<<<Post")
    }

    logMessage("completing main function...")
    //Thread.sleep(20000)

}


private suspend fun logHelloWorld() {
    logMessage("in logHelloWorld")
    helloMessage()
    worldMessage()
    logMessage("leaving logHelloWorld")
}

private suspend fun helloMessage() = withContext(Dispatchers.Default) {
    val myPrime = BigInteger.probablePrime(4096, Random())
    logMessage("Hello $myPrime")
}


private suspend fun worldMessage() = withContext(Dispatchers.Default) {
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