

## 关于 switch(R.id.xxx) 报"需要常量表达式"
// ❌ AGP 9 报错：需要常量表达式
switch (compoundButton.getId()) {
case R.id.tbtn_open:   // R.id 不再编译期常量
}

// ✅ 正确写法
int id = compoundButton.getId();
if (id == R.id.tbtn_open) { ... }
else if (id == R.id.swh_status) { ... }