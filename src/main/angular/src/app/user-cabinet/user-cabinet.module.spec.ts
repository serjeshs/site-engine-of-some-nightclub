import { UserCabinetModule } from './user-cabinet.module';

describe('UserCabinetModule', () => {
  let userCabinetModule: UserCabinetModule;

  beforeEach(() => {
    userCabinetModule = new UserCabinetModule();
  });

  it('should create an instance', () => {
    expect(userCabinetModule).toBeTruthy();
  });
});
