## Detekt

チーム内で以下の作業をする必要がある

1. projectのrootDirに移動
2. `$ cp git-hooks/pre-commit .git/hooks`を実行
3. `$ chmod +x .git/hooks/pre-commit`を実行
4. AndroidStudioで「Settings → Plugins → Detekt」をインストールする 

## Compose Atoms Design
粒度

**atoms** ： ボタンなどUIの最小構成をここにする。汎用性は高く保つ

個別のモデルには依存しないようにする


**molecules**： atomsに個別のデザインを適用したり、文言を固定したりある程度使用用途を明確にする。その他atomsの2,3以下程度の個数で構成されるものをここで定義する

リストの要素など
こことorganismsの使い分けが課題。他は割と明確

個別のモデルには依存しないようにする




**organisms**：ある特定のまとまりのあるUIパーツを定義する。中身が入った時に単一でUI的に意味があるものにする

文脈の適用はここでする
モデルに依存してもよい
ダイアログのうち汎用性のあるものはこの粒度とする


**templates**：画面要素として具体的なものを入れたらpagesになるようにする。実質的にここが各画面の定義になる。ただしViewModelには依存しないようにする（templates単独でPreviewを可能にするため）

ダイアログのうち画面専用の形式を作成したものはこの粒度とする（文言固定など他で使いまわせないもの）

xxxScreen#Content が該当


**pages**：ViewModelを取得してtemplatesのcomposableに実際の値を入れる。副作用系のUIの処理もここで行う。Previewは不要


xxxScreen#Screen が該当

## Architecture
[Rin](https://github.com/takahirom/Rin) を利用して**MVP**アーキテクチャで実装

#### Rin
**ViewModelと同等のライフサイクル**をComposition内で扱える様にしている。<br>
**Configuration change**などを超えて状態を保持する**State Holder**として、ViewModelは必須であり、**Navigation Compose**においては**back stack entry**へ状態を関連付ける上でも必須と言える。



参考:
- https://github.com/takahirom/Rin
- https://zenn.dev/rockname/articles/2a7db5ac4bb516
