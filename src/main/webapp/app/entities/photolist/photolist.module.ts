import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FjepaSharedModule } from '../../shared';
import {
    PhotolistService,
    PhotolistPopupService,
    PhotolistComponent,
    PhotolistDetailComponent,
    PhotolistDialogComponent,
    PhotolistPopupComponent,
    PhotolistDeletePopupComponent,
    PhotolistDeleteDialogComponent,
    photolistRoute,
    photolistPopupRoute,
    PhotolistResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...photolistRoute,
    ...photolistPopupRoute,
];

@NgModule({
    imports: [
        FjepaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PhotolistComponent,
        PhotolistDetailComponent,
        PhotolistDialogComponent,
        PhotolistDeleteDialogComponent,
        PhotolistPopupComponent,
        PhotolistDeletePopupComponent,
    ],
    entryComponents: [
        PhotolistComponent,
        PhotolistDialogComponent,
        PhotolistPopupComponent,
        PhotolistDeleteDialogComponent,
        PhotolistDeletePopupComponent,
    ],
    providers: [
        PhotolistService,
        PhotolistPopupService,
        PhotolistResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FjepaPhotolistModule {}
