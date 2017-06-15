#TestsForExtJSApp

<h3>Bugs</h3>
1. Grid have default ascending sorting instead of descending
2. When adding new letter, you can choose letter from drop down and add print additional space, after clicking button ADD empty row will be added
3. When adding new letter, print letter(don't choose from dropdown),  click button ADD, letter added to tree/grid after that button ADD disappear in tree/grid
4. Letters that was add to tree, appears in grid
5. When drop-down multiply lines from grid to tree all letters instead of first will have wrong icon

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