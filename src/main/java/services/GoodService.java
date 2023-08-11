package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.GoodConverter;
import actions.views.GoodView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Good;

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
     * @param gv いいねデータ
     */
    private void createInternal(GoodView gv) {

            em.getTransaction().begin();
            em.persist(GoodConverter.toModel(gv));
            em.getTransaction().commit();

    }

    /**
     * 指定した日報が作成したいいねデータを、指定されたページ数の一覧画面に表示する分取得しGoodViewのリストで返却する
     * @param report 日報
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<GoodView> getReportPerPage(ReportView report, int page) {

        List<Good> goods = em.createNamedQuery(JpaConst.Q_GOD_GET_REP, Good.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return GoodConverter.toViewList(goods);
    }

    /**
     * 指定した日報にされたいいねデータの件数を取得し、返却する
     * @param report
     * @return いいねデータの件数
     */
    public long countGoodReport(ReportView report) {

        long count = (long) em.createNamedQuery(JpaConst.Q_GOD_COUNT_REP, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();

        return count;
    }
}
