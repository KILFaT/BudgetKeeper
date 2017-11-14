import Vue from "vue/dist/vue.esm";
import App from "./App.vue";
import store from './store/store';
import {
  Button,
  Col,
  Dialog,
  Form,
  FormItem,
  Input,
  Option,
  Pagination,
  Row,
  Select,
  Table,
  TableColumn
} from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'
// more grace import third package !
import moment from 'moment'
import curvejs from 'curvejs'

Vue.config.productionTip = false;
Object.defineProperty(Vue.prototype, '$moment', { value: moment });
Object.defineProperty(Vue.prototype, '$curvejs', { value: curvejs });

Vue.use(Button);
Vue.use(Select);
Vue.use(Row);
Vue.use(Col);
Vue.use(Pagination);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Dialog);
Vue.use(Option);

locale.use(lang);

/* eslint-disable no-new */
new Vue({
          el: '#app',
          store,
          render: h => h(App)
        });
