package sample.controller.admin;

import static sample.api.admin.SystemAdminFacade.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sample.api.admin.SystemAdminFacade;
import sample.context.AppSetting;
import sample.context.AppSetting.FindAppSetting;
import sample.context.audit.*;
import sample.context.audit.AuditActor.FindAuditActor;
import sample.context.audit.AuditEvent.FindAuditEvent;
import sample.context.orm.PagingList;
import sample.controller.ControllerSupport;

/**
 * システムに関わる社内のUI要求を処理します。
 */
@RestController
@RequestMapping(Path)
public class SystemAdminController extends ControllerSupport {

    @Autowired
    SystemAdminFacade facade;

    /** 利用者監査ログを検索します。 */
    @RequestMapping(PathFindAudiActor)
    public PagingList<AuditActor> findAuditActor(@Valid FindAuditActor p) {
        return facade.findAuditActor(p);
    }

    /** イベント監査ログを検索します。 */
    @RequestMapping(PathFindAudiEvent)
    public PagingList<AuditEvent> findAuditEvent(@Valid FindAuditEvent p) {
        return facade.findAuditEvent(p);
    }

    /** アプリケーション設定一覧を検索します。 */
    @RequestMapping(PathFindAppSetting)
    public List<AppSetting> findAppSetting(@Valid FindAppSetting p) {
        return facade.findAppSetting(p);
    }

    /** アプリケーション設定情報を変更します。 */
    @RequestMapping(value = PathChangeAppSetting, method = RequestMethod.POST)
    public ResponseEntity<Void> changeAppSetting(@PathVariable String id, String value) {
        return facade.changeAppSetting(id, value);
    }

}
