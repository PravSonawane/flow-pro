package app.di

import dagger.Module
import data.flow.di.FlowModule

@Module(
    includes = [
        FlowModule::class
    ]
)
class AppModule
