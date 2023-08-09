package services;

import java.time.LocalDateTime;

import actions.views.GoodConverter;
import actions.views.GoodView;

/**
 * いいね詳細テーブルの操作に関わる処理を行うクラス
 */

public class GoodService extends ServiceBase{

    /**
     * いいねをした従業員を元にデータを1件作成し、いいねテーブルに登録する
     * @param gv 日報の登録内容
     */
    public void create(GoodView gv) {
            LocalDateTime now = LocalDateTime.now();
            gv.setCreatedAt(now);
            gv.setUpdatedAt(now);
            createInternal(gv);
    }

    /**
     * いいねデータを1件登録する
     * @param gv 日報データ
     */
    private void createInternal(GoodView gv) {

            em.getTransaction().begin();
            em.persist(GoodConverter.toModel(gv));
            em.getTransaction().commit();

    }

}
