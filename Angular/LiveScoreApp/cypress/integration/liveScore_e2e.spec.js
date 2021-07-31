// describe('My First Test', () => {
//     it('Does not do much!', () => {
//       expect(true).to.equal(true)
//     })
//   })
describe('Land in home page', () => {

    it('Visits the home page', () => {
        cy.visit("/")
    })
})

describe('Land in live page', () => {

    it('Visits the live matches page', () => {
        cy.visit("http://localhost:4200/live")
    })
})

describe('Land in login page', () => {

    it('Visits the login page', () => {
        
        cy.get('.login').click();
        cy.wait(4000);
        cy.visit("http://localhost:4200/login")
    })
})


describe('should check for user login', () => {

    it('should enter content in login ', () => {

        //  cy.get('input[id=username]').type("admin");

        cy.get(".clsuser").type('Ajay123');
        cy.get('.clspass').type('password');
        cy.wait(4000);
        cy.get('.clsbutton').click();
        cy.wait(4000);
        cy.location().should((location) => expect(location.href).to.eq('http://localhost:4200/live'));

    })
})

describe('Land in favourite list', () => {

    it('Visits the favourite list page', () => {
        
        cy.wait(4000);
        cy.get('.favs').click();
        cy.wait(4000);
        cy.visit("http://localhost:4200/favouriteList")
    })
})


describe('should check leagues', () => {

    it('should move leagues page', () => {
        cy.visit("/upcoming");
        cy.wait(4000);
        cy.location().should((location) => expect(location.href).to.eq('http://localhost:4200/upcoming'));
    })
})

describe('should check news', () => {

    it('should move news page', () => {
        cy.visit("/news");
        cy.wait(4000);
        cy.location().should((location) => expect(location.href).to.eq('http://localhost:4200/news'));
    })
})

describe('should check for logout', () => {

    it('should move to login page', () => {
        cy.get('.logout').click();
        cy.wait(4000);
        cy.location().should((location) => expect(location.href).to.eq('http://localhost:4200/login'));
    })
})
