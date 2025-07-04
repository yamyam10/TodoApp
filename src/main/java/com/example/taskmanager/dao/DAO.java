package com.example.taskmanager.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * データベース接続用の共通クラス。
 * JNDI を使って、Tomcat に設定されたデータソースからコネクションを取得する。
 */
public class DAO {
	// データソースのキャッシュ（初回取得後は使い回す）
	static DataSource ds;
	
	/**
     * データベースへの接続を取得するメソッド。
     * @return コネクションオブジェクト（呼び出し側で close が必要）
     * @throws Exception データソースの取得または接続に失敗した場合
     */
    public Connection getConnection() throws Exception {
        // データソースがまだ初期化されていない場合のみ取得する
        if (ds == null) {
            // 初期コンテキストを取得（JNDI を使うための準備）
            Context context = new InitialContext();
            
            // "java:/comp/env/jdbc/kikiyasuq" という名前で登録されたデータソースを取得
            // この名前は、web.xml や context.xml に定義されているリソース名と一致する必要がある
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/kikiyasuq");
        }
        
        // データソースからコネクションを取得して返す
        return ds.getConnection();
    }
}
