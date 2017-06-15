#TestsForExtJSApp

<h3>Run tests</h3>
<p>for Windows <code>gradlew clean test</code></p>
<p>for Unix <code>./gradlew clean test</code></p>

<h3>Test results</h3>
In root folder of project ./build/reports/tests/index.html


<h3>Bugs</h3>
1. Grid have default ascending sorting instead of descending
2. When adding new letter, you can choose letter from drop down and add print additional space, after clicking button ADD empty row will be added
3. When adding new letter, print letter(don't choose from dropdown),  click button ADD, letter added to tree/grid after that button ADD disappear in tree/grid
4. Letters that was add to tree, appears in grid
5. When drop-down multiply lines from grid to tree all letters instead of first will have wrong icon

<h3>Test cases</h3>
1. adaptive page layout (no automation, over-complex)
2. sorting tree
3. sorting grid
4. english alphabet
5. english letters
6. disable add button if nothing to add
7. drop-down tree -> grid
8. drop-down grid -> tree
9. delete from tree
10. delete from grid
11. add to grid(by selecting)
12. add to grid(by typing)
13. add to tree(by selecting)
14. add to tree(by typing)
15. multi-select work with grid
16. add window validation