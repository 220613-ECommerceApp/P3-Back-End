# Propane Gaming Backend
Backend for our slick new ECommerce website

## Contributing
1. Clone repo with `git clone https://github.com/220613-ECommerceApp/P3-Back-End.git`
2. Create branch locally through bash with `git checkout -b your-branch-name`
3. Make desired changes
    1. Change the profile in application.yml from `active: dev` to `active: production`
4. Add files to staging with `git add .` or individually with `git add filename.file`
5. Push to your branch the first time with `git push -u origin your-branch-name` or `git push --set-upstream origin your-branch-name`
    1. Subsequently you can push to the branch with a simple `git push`
6. Visit this repo here on github and create a pull request through the pull requests tab, or if there's a notification up you can click the "Compare & Pull Request" button
7. Get 2 people to approve your pull request
8. Merge when approved and delete your branch on github
9. Back on your local system, 
    1. `git checkout main`
    2. `git pull`
    3. `git branch -d your-branch-name`
    4. If you want to make new changes create a new branch the same way as stated before

## Getting started with H2
1. To run this backend simply run the ECommerceApplication.java
    1. You will need to change the active profile in application.yml to `active: dev`
2. Go to http://localhost:8080/h2-console to connect (The h2 console information should match the application.yml)
    1. driver: org.h2.Driver
    2. url: jdbc:h2:mem:memdb
    3. username: sa
    4. password: sa
3. If done correctly you should be connected to the h2 console
4. By default spring is going to create our tables for us based on the models. If you do not want this change `defer-datasource-initilization: true` in the application.yml to `defer-datasource-initilization: false`. Then update the data.sql script with the CREATE tables you want
5. The mock data is created in the script `data.sql`. You can modify this as needed to add mock data.
6. Run the front end with ng serve -o. by default this is hosted on http://localhost:4200. Angular should automatically open this in your browser.
    1. If `ng serve` is not recongnized as a command, you can try to run `npx ng serve` instead
