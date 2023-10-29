import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestConsumeApiComponent } from './test-consume-api.component';

describe('TestConsumeApiComponent', () => {
  let component: TestConsumeApiComponent;
  let fixture: ComponentFixture<TestConsumeApiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestConsumeApiComponent]
    });
    fixture = TestBed.createComponent(TestConsumeApiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
