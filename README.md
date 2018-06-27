# Command line instructions

## Git global setup
```
git config --global user.name "your_name"
git config --global user.email "your_email"
```

## Create a new repository
```
git clone https://git-courses.senlainc.com/senla_java_training/aliaksandr-kavalenka.git
cd aliaksandr-kavalenka
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master
```

## Existing folder
```
cd existing_folder
git init
git remote add origin https://git-courses.senlainc.com/senla_java_training/aliaksandr-kavalenka.git
git add .
git commit -m "Initial commit"
git push -u origin master
```

## Existing Git repository
```
cd existing_repo
git remote rename origin old-origin
git remote add origin https://git-courses.senlainc.com/senla_java_training/aliaksandr-kavalenka.git
git push -u origin --all
git push -u origin --tags
```