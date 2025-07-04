# TodoApp

Java（JSP + Servlet）および Oracle Database を使用したタスク管理アプリケーションです。  
タスクの登録、完了チェック、状態フィルター、締切日によるソート機能を備えています。

## 機能一覧

- タスクの登録（タイトル、締切日）
- タスクの一覧表示
- タスクの完了状態の更新（チェックボックスによる即時更新）
- 状態による絞り込み（すべて / 完了 / 未完了）
- 締切日による昇順 / 降順の並び替え

## 技術構成

- Java 21
- JSP / JSTL
- Servlet
- Oracle Database
- JDBC
- Apache Tomcat 10
- Eclipse（動的 Web プロジェクト）

## データベース構成

```sql
CREATE TABLE tasks (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100 CHAR) NOT NULL,
    deadline DATE,
    done NUMBER(1) DEFAULT 0 NOT NULL,
    created_at DATE DEFAULT SYSDATE
);

CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
```
