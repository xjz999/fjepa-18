import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Memuser } from './memuser.model';
import { MemuserPopupService } from './memuser-popup.service';
import { MemuserService } from './memuser.service';

@Component({
    selector: 'jhi-memuser-dialog',
    templateUrl: './memuser-dialog.component.html'
})
export class MemuserDialogComponent implements OnInit {

    memuser: Memuser;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private memuserService: MemuserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.memuser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.memuserService.update(this.memuser));
        } else {
            this.subscribeToSaveResponse(
                this.memuserService.create(this.memuser));
        }
    }

    private subscribeToSaveResponse(result: Observable<Memuser>) {
        result.subscribe((res: Memuser) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Memuser) {
        this.eventManager.broadcast({ name: 'memuserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-memuser-popup',
    template: ''
})
export class MemuserPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private memuserPopupService: MemuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.memuserPopupService
                    .open(MemuserDialogComponent as Component, params['id']);
            } else {
                this.memuserPopupService
                    .open(MemuserDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
