package com.cultureamp.digdag.plugin.example

import java.lang.reflect.Constructor
import java.util.{Arrays => JArrays, List => JList}

import io.digdag.client.config.Config
import io.digdag.util.BaseOperator
import io.digdag.spi.{
  Operator,
  OperatorContext,
  OperatorFactory,
  OperatorProvider,
  Plugin,
  TaskResult,
}
import javax.inject.Inject

object ExamplePlugin {
  class ExampleOperator(context: OperatorContext) extends BaseOperator(context) {
    override def runTask(): TaskResult = {
      println("ExampleOperator running...")
      return TaskResult.empty(request)
    }
  }

  class ExampleOperatorFactory extends OperatorFactory {
    override def getType(): java.lang.String =
      "eamon"

    override def newOperator(context: OperatorContext): Operator =
      new ExampleOperator(context);
  }

  class ExampleOperatorProvider extends OperatorProvider {

    override def get(): JList[OperatorFactory] = {
      JArrays.asList(
        new ExampleOperatorFactory(),
      )
    }
  }
}

class ExamplePlugin extends Plugin {
  override def getServiceProvider[T](`type`: Class[T]): Class[_ <: T] = {
    if (`type` ne classOf[OperatorProvider]) return null
    classOf[ExamplePlugin.ExampleOperatorProvider].asSubclass(`type`)
  }
}
