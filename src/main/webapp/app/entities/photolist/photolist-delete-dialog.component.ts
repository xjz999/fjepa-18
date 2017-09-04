import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Photolist } from './photolist.model';
import { PhotolistPopupService } from './photolist-popup.service';
import { PhotolistService } from './photolist.service';

@Component({
    selector: 'jhi-photolist-delete-dialog',
    templateUrl: './photolist-delete-dialog.component.html'
})
export class PhotolistDeleteDialogComponent {

    photolist: Photolist;

    constructor(
        private photolistService: PhotolistService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.photolistService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'photolistListModification',
                content: 'Deleted an photolist'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-photolist-delete-popup',
    template: ''
})
export class PhotolistDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private photolistPopupService: PhotolistPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.photolistPopupService
                .open(PhotolistDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
