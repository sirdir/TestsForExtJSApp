#TestsForExtJSApp

<h3>Bugs</h3>
1. Can't drop-down from grid to tree
2. Grid have default ascending sorting instead of descending
3. When adding new letter to grid, you can choose letter from drop down and add space, after clicking button ADD empty row will be added to grid
4. When adding new letter, print letter(don't choose from dropdown),  click button ADD, after that button ADD disappear(in both places, grid and tree)
5. Letters that was add to tree, appears in grid

<h3>Test cases</h3>
1. 35%
2. adaptive page layout
3. sorting tree
4. sorting grid
5. english alphabet
6. english letters
7. disable add button if nothing to add
8. drop-down tree -> grid
9. drop-down grid -> tree
10. delete from tree
11. delete from grid
12. add to grid(by selecting)
13. add to grid(by typing)
14. add to tree(by selecting)
15. add to tree(by typing)
16. multi-select work with grid
17. add window validation???

<h3>Run tests</h3>
<code>./gradlew clean test</code>