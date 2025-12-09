Notification module (Kotlin) - data/domain/presentation structure

Structure:
- data/remote: API service and DTOs
- data/repository: Repository implementation
- domain/entity: Domain models and repository interfaces
- domain/usecase: Use cases
- presentation/ui: Jetpack Compose screens
- presentation/viewmodel: ViewModel scaffold

Usage:
- Swap mock API calls with real Ktor client calls.
- Wire into DI (Koin/Hilt).
