package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Good;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class GoodConverer {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param gv GoodViewのインスタンス
     * @return Goodのインスタンス
     */
    public static Good toModel(GoodView gv) {
        return new Good(
                gv.getId(),
                ReportConverter.toModel(gv.getReport()),
                EmployeeConverter.toModel(gv.getEmployee()),
                gv.getCreatedAt(),
                gv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param g Goodのインスタンス
     * @return GoodViewのインスタンス
     */
    public static GoodView toView(Good g) {

        if (g == null) {
            return null;
        }

        return new GoodView(
                g.getId(),
                ReportConverter.toView(g.getReport()),
                EmployeeConverter.toView(g.getEmployee()),
                g.getCreatedAt(),
                g.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<GoodView> toViewList(List<Good> list) {
        List<GoodView> evs = new ArrayList<>();

        for (Good g : list) {
            evs.add(toView(g));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param g DTOモデル(コピー先)
     * @param gv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Good g, GoodView gv) {
        g.setId(gv.getId());
        g.setReport(ReportConverter.toModel(gv.getReport()));
        g.setEmployee(EmployeeConverter.toModel(gv.getEmployee()));
        g.setCreatedAt(gv.getCreatedAt());
        g.setUpdatedAt(gv.getUpdatedAt());

    }

}
