# درمانگرنو (DarmanagarNo) — v1.1.1

اپلیکیشن اندروید حرفه‌ای مدیریت نوبت، مراجع، جلسات، آزمون‌های بالینی و امور مالی برای روان‌شناسان و مشاوران.

> کار ما نیست شناسایی راز گل سرخ  
> کار ما شاید این است که در افسون گل سرخ شناور باشیم

**سازنده:** مهدی طریری — Madhitariri@gmail.com

---

## امکانات نسخه ۱.۱.۱

- صفحه امروز، یادآوری دقیق (AlarmManager)، مالی و SOAP
- بانک آزمون غربالگری و بالینی + کارگاه + خروجی Excel/PDF
- پشتیبان JSON و **پشتیبان رمزدار AES-GCM**
- قفل پین، بیومتریک، **قفل خودکار**
- کارت ویزیت دیجیتال با **ارسال به‌صورت تصویر** + QR (vCard)
- قالب SOAP، پیشنهاد زمان خالی، روند نمرات آزمون
- داشبورد سلامت کاری درمانگر
- تم‌های رنگی، ویجت نوبت بعدی
- فاکتور PDF رسمی، جابجایی سریع نوبت در تقویم، فیلتر لغو زیاد
- یادآوری هوشمند، سیاست حریم خصوصی فروشگاهی، CI با Secrets
- Room + SQLCipher · Migration تا ۱۰

---

## ساخت روی GitHub

ساختار مخزن باید **همین ریشه** باشد (نه پوشه تو در تو):

```text
/
├── app/
├── gradle/
├── .github/workflows/build.yml
├── gradlew
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

1. مخزن خالی بسازید و محتویات این پروژه را Push کنید روی `main` یا `master`.
2. به تب **Actions** بروید؛ workflow با نام **Build Release APK** اجرا می‌شود.
3. پس از موفقیت، Artifact با نام `darmanagarno-release-v1.1.1` را دانلود کنید.

اجرای دستی: **Actions → Build Release APK → Run workflow**

---

## ساخت محلی

```bash
chmod +x gradlew
./gradlew clean
./gradlew testDebugUnitTest
./gradlew assembleRelease
```

خروجی: `app/build/outputs/apk/release/app-release.apk`

### نیازمندی‌ها
| مورد | مقدار |
|------|--------|
| JDK | 17 |
| minSdk | 26 |
| targetSdk / compileSdk | 35 |
| Kotlin | 2.0 |
| UI | Jetpack Compose |
| Gradle | 8.9 |

---

## ساختار کد

```text
app/src/main/java/com/therapist/assistant/
  assessment/   بانک آزمون‌ها
  data/         Room، Repository، License، Settings
  notification/ یادآوری و BootReceiver
  ui/screens/   صفحات Compose
  ui/theme/     تم‌ها
  util/         تاریخ جلالی، بکاپ، PDF، کارت ویزیت، …
  viewmodel/    AppointmentViewModel
  widget/       ویجت نوبت بعدی
```

---

## نکات انتشار

- کلید امضای Release در `app/darmanagarno-release.jks` و تنظیمات در `app/build.gradle.kts` است.
- برای مخزن **عمومی** توصیه می‌شود بعداً کلید را به GitHub Secrets منتقل کنید.
- دیتابیس با SQLCipher رمزنگاری می‌شود؛ داده روی دستگاه می‌ماند.


## انتشار فروشگاهی
جزئیات در فایل `STORE.md`.
