package com.bonitasoft.engine.dsl.process

open class DataContainer(var dataList: MutableList<Data> = ArrayList()) {

    fun data(init: Data.() -> Unit) {
        val data = Data()

        data.init()
        dataList.add(data)
    }

}