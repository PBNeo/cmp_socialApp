Lounge module (Kotlin) - data / domain / presentation structure (full)

Includes:
- data/remote: DTOs + ApiService (mocked) + multipart upload stub
- data/repository: Repository implementation
- domain/entity: Lounge, LoungeRoom, Participant
- domain/repository: LoungeRepository
- domain/usecase: GetLounges, CreateLounge, GetLoungeDetails
- presentation/viewmodel: LoungeListViewModel, LoungeViewModel, CreateLoungeViewModel
- presentation/ui: LoungeListScreen, CreateLoungeSheet, LoungeRoomSheet, LoungeRoot
- di: loungeModule (Koin)

Notes:
- Replace mocked API with your Ktor client calls as needed.
- Multipart upload stub demonstrates how to upload images using a provided HttpClient.
- Bottom sheet wiring example included in LoungeRoot composable.
