; Script generated by the HM NIS Edit Script Wizard.

; HM NIS Edit Wizard helper defines
!define PRODUCT_NAME "AndroMDA XMI Exporter"
!define PRODUCT_VERSION "1.5"
!define PRODUCT_PUBLISHER "The AndroMDA Team"
!define PRODUCT_WEB_SITE "http://www.andromda.org"

!define PRODUCT_INST_KEY "Software\${PRODUCT_NAME}"
!define PRODUCT_INST_ROOT_KEY "HKLM"

!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

; MUI 1.67 compatible ------
!include "MUI.nsh"

; MUI Settings
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

; Welcome page
!insertmacro MUI_PAGE_WELCOME
; Directory page
!insertmacro MUI_PAGE_DIRECTORY
; Instfiles page
!insertmacro MUI_PAGE_INSTFILES
; Finish page
!insertmacro MUI_PAGE_FINISH

; Uninstaller pages
!insertmacro MUI_UNPAGE_INSTFILES

; Language files
!insertmacro MUI_LANGUAGE "English"

; MUI end ------

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "AndroMDATogetherSupport-${PRODUCT_VERSION}.exe"
InstallDir "$PROGRAMFILES\TogetherDesigner\"
InstallDirRegKey ${PRODUCT_INST_ROOT_KEY} "${PRODUCT_INST_KEY}" "InstallDir"
ShowInstDetails show
ShowUnInstDetails show

Section "XMI Exporter" SEC01
  SetOutPath "$INSTDIR\modules\org.andromda.modules.xmilink"
  SetOverwrite try
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.xmilink\dist\commons-lang-2.0.jar"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.xmilink\dist\module.xml"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.xmilink\dist\xmilink.jar"
  SetOutPath "$INSTDIR\bin"
  CopyFiles $INSTDIR\bin\Together.exe $INSTDIR\bin\XMIExport.exe
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.xmilink\launcher\XMIExport.config"
SectionEnd

Section "Spring Profile" SEC02
  SetOverwrite try
  SetOutPath "$INSTDIR\modules\org.andromda.modules.profiles.spring"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.profiles.spring\dist\module.xml"
  SetOutPath "$INSTDIR\modules\org.andromda.modules.profiles.spring\profile\spring"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.profiles.spring\dist\profile\spring\diagram.config"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.profiles.spring\dist\profile\spring\profile.config"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.profiles.spring\dist\profile\spring\viewmap.config"
  SetOutPath "$INSTDIR\modules\org.andromda.modules.profiles.spring"
  File "D:\develop\workspaces\workspace-eclipse_3_1-andromda\org.andromda.modules.profiles.spring\dist\profile-spring.jar"
SectionEnd

Section -Post
  WriteUninstaller "$INSTDIR\uninst-andromdaxmi.exe"
  WriteRegStr ${PRODUCT_INST_ROOT_KEY} "${PRODUCT_INST_KEY}" "InstallDir" "$INSTDIR"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "URLInfoAbout" "${PRODUCT_WEB_SITE}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
SectionEnd


Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "$(^Name) was successfully removed from your computer."
FunctionEnd

Function un.onInit
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "Are you sure you want to completely remove $(^Name) and all of its components?" IDYES +2
  Abort
FunctionEnd

Section Uninstall
  Delete "$INSTDIR\uninst-andromdaxmi.exe"
  Delete "$INSTDIR\modules\org.andromda.modules.profiles.spring\profile-spring.jar"
  Delete "$INSTDIR\modules\org.andromda.modules.profiles.spring\profile\spring\viewmap.config"
  Delete "$INSTDIR\modules\org.andromda.modules.profiles.spring\profile\spring\profile.config"
  Delete "$INSTDIR\modules\org.andromda.modules.profiles.spring\profile\spring\diagram.config"
  Delete "$INSTDIR\modules\org.andromda.modules.profiles.spring\module.xml"
  Delete "$INSTDIR\bin\XMIExport.config"
  Delete "$INSTDIR\bin\XMIExport.exe"
  Delete "$INSTDIR\modules\org.andromda.modules.xmilink\xmilink.jar"
  Delete "$INSTDIR\modules\org.andromda.modules.xmilink\module.xml"
  Delete "$INSTDIR\modules\org.andromda.modules.xmilink\commons-lang-2.0.jar"

  RMDir /r "$INSTDIR\modules\org.andromda.modules.profiles.spring"
  RMDir /r "$INSTDIR\modules\org.andromda.modules.xmilink"

  DeleteRegKey ${PRODUCT_INST_ROOT_KEY} "${PRODUCT_INST_KEY}"
  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
  SetAutoClose true
SectionEnd