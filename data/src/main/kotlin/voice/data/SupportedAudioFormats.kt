package voice.data

import voice.documentfile.CachedDocumentFile
import voice.documentfile.walk

private val supportedAudioFormats = setOf(
  "3gp",
  "aac",
  "awb",
  "flac",
  "imy",
  "m4a",
  "m4b",
  "mid",
  "mka",
  "mkv",
  "mp3",
  "mp3package",
  "mp4",
  "mpga",
  "mxmf",
  "oga",
  "ogg",
  "ogx",
  "opus",
  "ota",
  "rtttl",
  "rtx",
  "wav",
  "webm",
  "xmf",
)

fun CachedDocumentFile.isAudioFile(): Boolean {
  if (!isFile) return false
  val name = name ?: return false
  val extension = name.substringAfterLast(".").lowercase()
  return extension in supportedAudioFormats
}

fun CachedDocumentFile.audioFileCount(): Int {
  return if (isAudioFile()) {
    1
  } else {
    walk().count { it.isAudioFile() }
  }
}
