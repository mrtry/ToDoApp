# ToDoApp

タスクのCRUDができるToDoアプリ

- [DeployGate](https://dply.me/vma0o3)

## Features

| create & read | update | delete|
| --- | --- | --- |
| <img src="https://user-images.githubusercontent.com/8851552/95656786-9a0e1900-0b4b-11eb-88ac-b2ebb8916436.gif" /> | <img src="https://user-images.githubusercontent.com/8851552/95675966-e4e46b00-0bf5-11eb-9929-e4a095925c0d.gif" /> | <img src="https://user-images.githubusercontent.com/8851552/95676957-288ea300-0bfd-11eb-87c7-48a5a87c89bd.gif" /> |

## 開発環境

Android 4.0.1で開発

## 設計

MVVM + Layerd Arch(Repository、DomainService、etc) に近い感じで統一されている

非同期処理はCoroutineを利用している
ログインとデータ管理についてはFirebase(Auth、Firestore)を利用している

### ディレクトリ構成

```
app/src/main/java/io/github/mrtry/todolist/
├── app // UIまわりをドメイン別に区切ってある
│   ├── splash // スプラッシュ画面
│   │   ├── ui
│   │   │   ├── navigator
│   │   │   └── result
│   │   └── viewmodel
│   └── todo // ログイン後のToDoタスク画面
│       ├── ui
│       │   ├── adapter
│       │   ├── menu
│       │   └── navigator
│       └── viewmodel
│           └── converter
├── auth // 認証まわり
│   └── repository
├── di // DIまわり
│   ├── component
│   ├── module
│   ├── qualifier
│   ├── scope
│   └── utils
├── misc // 拡張関数や共通利用できるUIまわりのあれこれ
│   ├── extension
│   └── ui
│       ├── binding
│       ├── menu
│       ├── navigator
│       ├── result
│       ├── view
│       └── viewmodel
└── task // ToDo Taskまわり
    ├── domainservice
    ├── entity
    └── repository
```

## デザイン

Material Design Guidelineに概ね従って整えてある

## テスト

ViewModelについてのユニットテストは最低限書いてあるが、他は書いていない
一部Roborectricを利用するテストが動作しないためコメントアウトしてある(環境構築に問題があるのかもしれない)

UIテストまでは書いていない

## CI/CD

GitHub Actionsで最低限設定してある

- deploy branchにmergeするタイミングで、ユニットテストを実行するようにしてある
- main branchにmergeするタイミングで、DeplopyGateへデプロイをするようにしてある

### アプリを手元でビルドするときの注意点

- CI上では、`google-service.json`を[secrets](https://github.com/mrtry/ToDoApp/settings/secrets)に入れてあるのでビルドができるが、手元でやるときは自前で準備する必要がある
