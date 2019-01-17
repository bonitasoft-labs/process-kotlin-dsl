package com.bonitasoft.engine.dsl.process


fun process(name: String, version: String, init: Process.() -> Unit): Process {
    val process = Process(name, version)
    process.init()
    return process
}