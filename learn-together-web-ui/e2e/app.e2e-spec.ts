import { LearnTogetherWebUiPage } from './app.po';

describe('learn-together-web-ui App', () => {
  let page: LearnTogetherWebUiPage;

  beforeEach(() => {
    page = new LearnTogetherWebUiPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
