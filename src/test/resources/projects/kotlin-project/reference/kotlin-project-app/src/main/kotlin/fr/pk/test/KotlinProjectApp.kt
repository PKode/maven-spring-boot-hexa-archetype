package fr.pk.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinProjectApp

fun main(args: Array<String>) {
    runApplication<KotlinProjectApp>(*args)
}
