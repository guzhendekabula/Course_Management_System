import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Layout from '../views/layout_interface.vue'

const routes = [
  {
    path:"/",
    name:"Layout",
    component:Layout,
    children:[
      {
        path: '',
        name: 'home',
        component: HomeView,
      },
      {
        path: 'personalpage',
        name: 'personalpage',
        component: ()=>import("../views/main/personalpage_interface"),
      },
      {
        path: 'adm_interface',
        name: 'adm_interface',
        component: ()=>import("../views/main/adm_interface"),
        children:[
          {
            path: 'adm_approval_form_interface',
            name: 'adm_approval_form_interface',
            component: ()=>import("../views/main/adm_approval_form_interface"),
          },
          {
            path: 'adm_course_score_interface',
            name: 'adm_course_score_interface',
            component: ()=>import("../views/main/adm_course_score_interface"),
          },
          {
            path: 'adm_evaluation_interface',
            name: 'adm_evaluation_interface',
            component: ()=>import("../views/main/adm_evaluation_interface"),
          },
          {
            path: 'adm_courses',
            name: 'adm_courses',
            component: ()=>import("../views/main/adm_courses"),
          },
          {
            path: 'adm_user_management',
            name: 'adm_user_management',
            component: ()=>import("../views/main/adm_user_management"),
            children:[
              {
                path: 'adm_view_user',
                name: 'adm_view_user',
                component: ()=>import("../views/main/adm_view_user.vue"),
              },
              {
                path: 'adm_edit_user',
                name: 'adm_edit_user',
                component: ()=>import("../views/main/adm_edit_user.vue"),
              },
              {
                path: 'adm_add_user',
                name: 'adm_add_user',
                component: ()=>import("../views/main/adm_add_user.vue"),
              }
            ]
          },
          {
            path: 'adm_personal_info_interface',
            name: 'adm_personal_info_interface',
            component: ()=>import("../views/main/adm_personal_info_interface"),
            children:[
              {
                path: 'adm_change_personal_info',
                name: 'adm_change_personal_info',
                component: ()=>import("../views/main/adm_change_personal_info.vue"),
              }
            ]
          },
          {
            path: 'adm_activity_interface',
            name: 'adm_activity_interface',
            component: ()=>import("../views/main/adm_activity_interface"),
          },
        ]
      },
      {
        path: 'stu_interface',
        name: 'stu_interface',
        component: ()=>import("../views/main/stu_interface"),
        children:[
          {
            path: 'stu_class_table_interface',
            name: 'stu_class_table_interface',
            component: ()=>import("../views/main/stu_class_table_interface"),
          },
          {
            path: 'stu_evaluation_interface',
            name: 'stu_evaluation_interface',
            component: ()=>import("../views/main/stu_evaluation_interface"),
          },
          {
            path: 'stu_score_interface',
            name: 'stu_score_interface',
            component: ()=>import("../views/main/stu_score_interface"),
          },
          {
            path: 'stu_select_courses',
            name: 'stu_select_courses',
            component: ()=>import("../views/main/stu_select_courses"),
          },
          {
            path: 'stu_manage_courses',
            name: 'stu_manage_courses',
            component: ()=>import("../views/main/stu_manage_courses"),
          },
          {
            path: 'stu_personal_info_interface',
            name: 'stu_personal_info_interface',
            component: ()=>import("../views/main/stu_personal_info_interface"),
            children:[
              {
                path: 'stu_change_personal_info',
                name: 'stu_change_personal_info',
                component: ()=>import("../views/main/stu_change_personal_info.vue"),
              }
            ]
          }
        ]
      },
      {
        path: 'tch_interface',
        name: 'tch_interface',
        component: ()=>import("../views/main/tch_interface"),
        children:[
          {
            path: 'tch_approval_form_interface',
            name: 'tch_approval_form_interface',
            component: ()=>import("../views/main/tch_approval_form_interface"),
            children:[
              {
                path: 'tch_editing_course',
                name: 'tch_editing_course',
                component: ()=>import("../views/main/tch_editing_course.vue"),
              },
              {
                path: 'tch_apply_course',
                name: 'tch_apply_course',
                component: ()=>import("../views/main/tch_apply_course.vue"),
              }
            ]
          },
          {
            path: 'tch_class_table_interface',
            name: 'tch_class_table_interface',
            component: ()=>import("../views/main/tch_class_table_interface"),
          },
          {
            path: 'tch_course_score_interface',
            name: 'tch_course_score_interface',
            component: ()=>import("../views/main/tch_course_score_interface"),
          },
          {
            path: 'tch_evaluation_interface',
            name: 'tch_evaluation_interface',
            component: ()=>import("../views/main/tch_evaluation_interface"),
          },
          {
            path: 'tch_grade',
            name: 'tch_grade',
            component: ()=>import("../views/main/tch_grade"),
            children:[
              {
                path: 'tch_specific_grade',
                name: 'tch_specific_grade',
                component: ()=>import("../views/main/tch_specific_grade.vue"),
              }
            ]
          },
          {
            path: 'tch_personal_info_interface',
            name: 'tch_personal_info_interface',
            component: ()=>import("../views/main/tch_personal_info_interface.vue"),
            children:[
              {
                path: 'tch_change_personal_info',
                name: 'tch_change_personal_info',
                component: ()=>import("../views/main/tch_change_personal_info.vue"),
              }
            ]
          }
        ]
      }
    ]
  },
  {
    path:'/login_interface',
    name:'login_interface',
    component: ()=>import("../views/login_interface.vue")
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
