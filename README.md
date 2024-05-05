# SpringBootDemo

Controller RestTemplateController

application.propreties spring.profiles.active=prod 配置切换文件dev/prod

application.yml

application-dev.yml application-prod.yml

###### 跨域设置

@CrossOrigin

## UserController

<br>import org.springframework.data.domain.Page;
<br>http://localhost:8080/user/page?pageNum=1&pageSize=10&name=王
<br>Result<Page<UserR>> findPage

## StringController

trim()
<br>trim()用于删除字符串开头和结尾的空白字符

## ListController

### Stream

#### 生成流的方式五种

**1、Stream创建**
<br>`List<String> list = new ArrayList<>(); `
<br>`Stream<String> stream = list.stream(); `
<br>//串行流 Stream<String>
<br>`parallelStream = list.parallelStream();`
<br>//并行流
<br>`Stream<Integer> stream1 = Stream.of(1,2,3,4,5); `

**2、Collection集合创建（应用中最常用的一种）**
<br>`List<Integer> integerList = new ArrayList<>();`
<br>`integerList.add(1);`
<br>`integerList.add(2);`
<br>`integerList.add(3);`
<br>`integerList.add(4);`
<br>`integerList.add(5);`
<br>`Stream<Integer> listStream = integerList.stream();`

**3、Array数组创建**
int[] intArr = {1, 2, 3, 4, 5};`
<br>`IntStream arrayStream = Arrays.stream(intArr);`
<br>通过Arrays.stream方法生成流，并且该方法生成的流是数值流【即IntStream】而不是 Stream 注： 使用数值流可以避免计算过程中拆箱装箱，提高性能。 Stream
API提供了mapToInt、mapToDouble、mapToLong三种方式将对象流【即Stream 】转换成对应的数值流，同时提供了boxed方法将数值流转换为对象流

**4、文件创建**
<br>`try { Stream<String> fileStream = Files.lines(Paths.get("data.txt"), Charset.defaultCharset()); } catch (
IOException e) { e.printStackTrace(); }`
<br>通过Files.line方法得到一个流，并且得到的每个流是给定文件中的一行

**5、函数创建**
iterate方法接受两个参数，第一个为初始化值，第二个为进行的函数操作，因为iterator生成的流为无限流，通过limit方法对流进行了截断，只生成5个偶数 iterator Stream<Integer> iterateStream
= Stream.iterate(0, n -> n + 2).limit(5);`

generator Stream<Double> generateStream = Stream.generate(Math::random).limit(5); generate方法接受一个参数，方法参数类型为Supplier
，由它为流提供值。generate生成的流也是无限流，因此通过limit对流进行了截断

Stream中的静态方法：of()、iterate()、generate()

<br>`Stream<Integer> stream = Stream.of(1,2,3,4,5,6);`
<br>`stream.forEach(System.out::println);`  
<br>// 输出：1 2 3 4 5 6
<br>`Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);`
<br>`stream2.forEach(System.out::println);`
<br>// 输出：0 2 4 6 8 10
<br>`Stream<Double> stream3 = Stream.generate(Math::random).limit(2); stream3.forEach(System.out::println);`
// 输出：两个随机数

BufferedReader.lines() 方法，将每行内容转成流
<br>`BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));`
<br>`Stream<String> lineStream = reader.lines(); lineStream.forEach(System.out::println);`
<br>Pattern.splitAsStream() 方法，将字符串分隔成流

Pattern pattern = Pattern.compile(",");`
<br>`Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");`
<br>`stringStream.forEach(System.out::println);`  
//输出：a b c d

#### 中间操作符

通常对于Stream的中间操作，可以视为是源的查询，并且是懒惰式的设计，对于源数据进行的计算只有在需要时才会被执行，与数据库中视图的原理相似；
Stream流的强大之处便是在于提供了丰富的中间操作，相比集合或数组这类容器，极大简化源数据的计算复杂度 一个流可以跟随零个或多个中间操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用
这类操作都是惰性化的，仅仅调用到这类方法，并没有真正开始流的遍历，真正的遍历需等到终端操作时，常见的中间操作有下面即将介绍的 filter、map 等

#### 终端操作符

Stream流执行完终端操作之后，无法再执行其他动作，否则会报状态异常，提示该流已经被执行操作或者被关闭，想要再次执行操作必须重新创建Stream流
一个流有且只能有一个终端操作，当这个操作执行后，流就被关闭了，无法再被操作，因此一个流只能被遍历一次，若想在遍历需要通过源数据在生成流。 终端操作的执行，才会真正开始流的遍历。如 count、collect 等

#### Stream使用方法

###### 中间操作符使用

**filter** 用于通过设置的条件过滤出元素
<br>`List strings = Arrays.asList(“abc”, “”, “bc”, “efg”, “abcd”,"", “jkl”);`
<br>`List filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());`

**map** 接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一个新版本”而不是去“修改”）
<br>`List strings = Arrays.asList(“abc”, “abc”, “bc”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`List mapped = strings.stream().map(str->str+"-IT").collect(Collectors.toList());`

**distinct** 返回一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流
<br>`List numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);`
<br>`numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println); `

**sorted** 返回排序后的流
<br>`List strings1 = Arrays.asList(“abc”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`List sorted1 = strings1.stream().sorted().collect(Collectors.toList());`

**limit** 会返回一个不超过给定长度的流
<br>`List strings = Arrays.asList(“abc”, “abc”, “bc”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`List limited = strings.stream().limit(3).collect(Collectors.toList());`

**skip** 返回一个扔掉了前n个元素的流
<br>`List strings = Arrays.asList(“abc”, “abc”, “bc”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`List skiped = strings.stream().skip(3).collect(Collectors.toList());`

**flatMap** 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
<br>`List strings = Arrays.asList(“abc”, “abc”, “bc”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`Stream flatMap = strings.stream().flatMap(Java8StreamTest::getCharacterByString);`

**peek** 对元素进行遍历处理 	
`List strings = Arrays.asList(“abc”, “abc”, “bc”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`strings .stream().peek(str-> str + "a").forEach(System.out::println);`

###### 终端操作符使用

**collect** 收集器，将流转换为其他形式
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`Set set = strings.stream().collect(Collectors.toSet());`
<br>`List list = strings.stream().collect(Collectors.toList());`
<br>`Map<String, String> map = strings.stream().collect(Collectors.toMap(v ->v.concat("_name"), v1 -> v1, (v1, v2) -> v1));`

**forEach** 遍历流
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);strings.stream().forEach(s -> out.println(s));`

**findFirst** 返回第一个元素
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`Optional first = strings.stream().findFirst();`

**findAny** 将返回当前流中的任意元素
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`Optional any = strings.stream().findAny();`

**count** 返回流中元素总数
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`long count = strings.stream().count();`

**sum** 求和
<br>`int sum = userList.stream().mapToInt(User::getId).sum();`

**max** 最大值
<br>`int max = userList.stream().max(Comparator.comparingInt(User::getId)).get().getId();`

**min** 最小值
<br>`int min = userList.stream().min(Comparator.comparingInt(User::getId)).get().getId();`

**anyMatch** 检查是否至少匹配一个元素，返回boolean
<br>`List strings = Arrays.asList(“abc”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`boolean b = strings.stream().anyMatch(s -> s == “abc”);`

**allMatch** 检查是否匹配所有元素，返回boolean
<br>`List strings = Arrays.asList(“abc”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`boolean b = strings.stream().allMatch(s -> s == “abc”);`

**noneMatch** 检查是否没有匹配所有元素，返回boolean
<br>`List strings = Arrays.asList(“abc”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`boolean b = strings.stream().noneMatch(s -> s == “abc”);`

**reduce** 可以将流中元素反复结合起来，得到一个值
<br>`List strings = Arrays.asList(“cv”, “abd”, “aba”, “efg”, “abcd”,“jkl”, “jkl”);`
<br>`Optional reduce = strings.stream().reduce((acc,item) -> {return acc+item;});if(reduce.isPresent())out.println(reduce.get());`

###### Collect收集

Collector：结果收集策略的核心接口，具备将指定元素累加存放到结果容器中的能力；并在Collectors工具中提供了Collector接口的实现类

1、toList 将用户ID存放到List集合中
<br>`List<Integer> idList = userList.stream().map(User::getId).collect(Collectors.toList()) ;`

2、toMap 将用户ID和Name以Key-Value形式存放到Map集合中
<br>`Map<Integer,String> userMap = userList.stream().collect(Collectors.toMap(User::getId,User::getName));`

3、toSet 将用户所在城市存放到Set集合中
<br>`Set<String> citySet = userList.stream().map(User::getCity).collect(Collectors.toSet());`

4、counting 符合条件的用户总数
<br>`long count = userList.stream().filter(user -> user.getId()>1).collect(Collectors.counting());`

5、summingInt 对结果元素即用户ID求和
<br>`Integer sumInt = userList.stream().filter(user -> user.getId()>2).collect(Collectors.summingInt(User::getId)) ;`

6、minBy 筛选元素中ID最小的用户
<br>`User maxId = userList.stream().collect(Collectors.minBy(Comparator.comparingInt(User::getId))).get() ;`

7、joining 将用户所在城市，以指定分隔符链接成字符串；
<br>`String joinCity = userList.stream().map(User::getCity).collect(Collectors.joining("||"));`

8、groupingBy 按条件分组，以城市对用户进行分组；
<br>`Map<String,List<User>> groupCity = userList.stream().collect(Collectors.groupingBy(User::getCity));`

1、orElse(null)

    /**
     * Return the value if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no value present, may
     * be null
     * @return the value, if present, otherwise {@code other}
     * 返回值，如果存在，否则返回其他
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

表示如果一个都没找到返回null（orElse()中可以塞默认值。如果找不到就会返回orElse中设置的默认值）

2、orElseGet(null)

    /**
     * Return the value if present, otherwise invoke {@code other} and return
     * the result of that invocation.
     *
     * @param other a {@code Supplier} whose result is returned if no value
     * is present
     * @return the value if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if value is not present and {@code other} is
     * null
     * 返回值如果存在，否则调用其他值并返回该调用的结果
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

表示如果一个都没找到返回null（orElseGet()中可以塞默认值。如果找不到就会返回orElseGet中设置的默认值） orElse（） 接受类型T的
任何参数，而orElseGet（）接受类型为Supplier的函数接口，该接口返回类型为T的对象 orElse(null)和orElseGet(null)区别：
<br>1、当返回Optional的值是空值null时，无论orElse还是orElseGet都会执行
<br>2、而当返回的Optional有值时，orElse会执行，而orElseGet不会执行



