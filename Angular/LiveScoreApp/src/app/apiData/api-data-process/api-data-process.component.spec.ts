import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiDataProcessComponent } from './api-data-process.component';

describe('ApiDataProcessComponent', () => {
  let component: ApiDataProcessComponent;
  let fixture: ComponentFixture<ApiDataProcessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApiDataProcessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiDataProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
