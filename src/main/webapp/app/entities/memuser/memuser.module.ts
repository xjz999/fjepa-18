import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FjepaSharedModule } from '../../shared';
import {
    MemuserService,
    MemuserPopupService,
    MemuserComponent,
    MemuserDetailComponent,
    MemuserDialogComponent,
    MemuserPopupComponent,
    MemuserDeletePopupComponent,
    MemuserDeleteDialogComponent,
    memuserRoute,
    memuserPopupRoute,
    MemuserResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...memuserRoute,
    ...memuserPopupRoute,
];

@NgModule({
    imports: [
        FjepaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MemuserComponent,
        MemuserDetailComponent,
        MemuserDialogComponent,
        MemuserDeleteDialogComponent,
        MemuserPopupComponent,
        MemuserDeletePopupComponent,
    ],
    entryComponents: [
        MemuserComponent,
        MemuserDialogComponent,
        MemuserPopupComponent,
        MemuserDeleteDialogComponent,
        MemuserDeletePopupComponent,
    ],
    providers: [
        MemuserService,
        MemuserPopupService,
        MemuserResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FjepaMemuserModule {}
