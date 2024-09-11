package ui.util

sealed class MoveState {
    object None : MoveState()
    object Walking : MoveState()
    object Horse : MoveState()
    object Flying : MoveState()
}
//
//fun selectState(state: MoveState): String {
//    return when (state) {
//        MoveState.None ->
//            "Выберите способ передвижения"
//
//        MoveState.Walking ->
//            "Идти пешком"
//
//        MoveState.Horse ->
//            "Ехать на лошади"
//
//        MoveState.Flying ->
//            "Лететь"
//    }
//}


