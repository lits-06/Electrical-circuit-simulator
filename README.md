# Java OOP Project: Electrical circuit simulator

## Danh sách thành viên

- 20220072 - Đoàn Tiến Dũng
- 2022xxxx - Phạm Trường Sang
- 2022xxxx - Đinh Xuân Toàn
- 2022xxxx -

## Lưu ý

Tạo nhánh mới trước khi viết và đẩy code lên. Ex: `tên\task`

Check task trên Trello, khi đẩy code lên thì tạo pull request.

Đề xuất thì viết vào issue.

## Cấu hình

Với ai dùng vscode thì tạo file `launch.json` ở trong folder `.vscode`

Nhớ sửa lại `<JavaFX_lib_path>`. Ví dụ: `path/to/java/javafx-sdk-22.0.1/lib`

```launch.json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch App",
            "request": "launch",
            "mainClass": "Main",
            "vmArgs": "--module-path <JavaFX_lib_path> --add-modules javafx.controls,javafx.fxml",
        }
    ]
}
```

Đọc thêm ở https://openjfx.io/openjfx-docs/#introduction

## Cấu trúc dự án

```
Electrical circuit simulator
├── bin
└── src
    ├── app
    ├── gui
    ├── models
    └── resources

```

`/bin`: lưu trữ các file java biên dịch

`/src`: source code của dự án

`/gui`: các class liên quan đến gui

`/models`: các class của phần tử mạch điện

`/resources`: hình ảnh,...

`/app`: class chính để chạy code
