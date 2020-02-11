package data.flow.di

import domain.flow.repositories.FlowRepository

interface FlowComponent {
    fun flowRepository(): FlowRepository
}