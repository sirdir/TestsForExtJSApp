#TestsForExtJSApp

<h3>Bugs</h3>
1. Can't drop-down from grid to tree
2. Grid have default ascending sorting instead of descending
3. When adding new letter to grid, you can choose letter from drop down and add space, after clicking button ADD empty row will be added to grid
4. When adding new letter, print letter(don't choose from dropdown),  click button ADD, after that button ADD disappear(in both places, grid and tree)
5. Letters that was add to tree, appears in grid

<h3>Test cases</h3>
1. 35%
1. adaptive page layout
1. sorting tree
1. sorting grid
1. english alphabet
1. english letters
9. disable add button if nothing to add
1. drop-down tree -> grid
2. drop-down grid -> tree
3. delete from tree
4. delete from grid
5. add to grid(by selecting)
6. add to grid(by typing)
7. add to tree(by selecting)
8. add to tree(by typing)
9. multi-select work with grid
9. add window validation???

<h3>Run tests</h3>
<code>./gradlew clean test</code>