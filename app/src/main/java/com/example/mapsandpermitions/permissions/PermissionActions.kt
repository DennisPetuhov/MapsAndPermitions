
package com.example.mapsandpermitions.permissions

sealed class PermissionAction {
  object PermissionGranted : PermissionAction()
  object PermissionDenied : PermissionAction()
}
