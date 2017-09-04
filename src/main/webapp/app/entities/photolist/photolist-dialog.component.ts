import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Photolist } from './photolist.model';
import { PhotolistPopupService } from './photolist-popup.service';
import { PhotolistService } from './photolist.service';

@Component({
    selector: 'jhi-photolist-dialog',
    templateUrl: './photolist-dialog.component.html'
})
export class PhotolistDialogComponent implements OnInit {

    photolist: Photolist;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private photolistService: PhotolistService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.photolist.id !== undefined) {
            this.subscribeToSaveResponse(
                this.photolistService.update(this.photolist));
        } else {
            this.subscribeToSaveResponse(
                this.photolistService.create(this.photolist));
        }
    }

    private subscribeToSaveResponse(result: Observable<Photolist>) {
        result.subscribe((res: Photolist) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Photolist) {
        this.eventManager.broadcast({ name: 'photolistListModification', content: 'OK'});
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
    selector: 'jhi-photolist-popup',
    template: ''
})
export class PhotolistPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private photolistPopupService: PhotolistPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.photolistPopupService
                    .open(PhotolistDialogComponent as Component, params['id']);
            } else {
                this.photolistPopupService
                    .open(PhotolistDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
