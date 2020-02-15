package core.lib.usecase

interface UseCase<Input, Output> {
    operator fun invoke(input: Input): Output
}
