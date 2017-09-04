import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { FjepaMemuserModule } from './memuser/memuser.module';
import { FjepaNewsModule } from './news/news.module';
import { FjepaPhotolistModule } from './photolist/photolist.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        FjepaMemuserModule,
        FjepaNewsModule,
        FjepaPhotolistModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FjepaEntityModule {}
