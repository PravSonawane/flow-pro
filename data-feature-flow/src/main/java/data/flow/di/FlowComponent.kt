package data.flow.di

import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository

interface FlowComponent {
    fun flowRepository(): FlowRepository
    fun nodeRepository(): NodeRepository
}
