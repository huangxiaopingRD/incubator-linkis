<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~   http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  -->

<template>
  <div>
    <div class="layout-header">
      <div
        class="layout-header-menu-icon"
      >
        <div class="logo">
          <img
            class="logo-img"
            src=""
            :alt="$t('message.common.logoName')"
          >
          <span class="version">{{sysVersion}}</span>
        </div>
      </div>
      <div
        v-clickoutside="handleOutsideClick"
        :class="{'selected': isUserMenuShow}"
        class="user"
        @click="handleUserClick"
      >
        <span>{{ userName }}</span>
        <Icon v-show="!isUserMenuShow" type="ios-arrow-down" class="user-icon"/>
        <Icon v-show="isUserMenuShow" type="ios-arrow-up" class="user-icon"/>
        <userMenu v-show="isUserMenuShow" @clear-session="clearSession"/>
      </div>
      <ul class="menu">
        <li class="menu-item" @click="goConsole">{{$t("message.common.management")}}</li>
      </ul>
      <div class="icon-group">
        <Icon
          v-if="isSandbox"
          :title="$t('message.common.home')"
          class="book"
          type="ios-chatboxes"
          @click="linkTo('freedback')"
        ></Icon>
      </div>
    </div>
  </div>
</template>
<script>
import userMenu from "./userMenu.vue";
import clickoutside from "@/common/helper/clickoutside";
import mixin from '@/common/service/mixin';
import util from '@/common/util';
export default {
  directives: {
    clickoutside
  },
  components: {
    userMenu
  },
  data() {
    return {
      sysVersion: process.env.VUE_APP_VERSION,
      isUserMenuShow: false,
      userName: "",
      isSandbox: process.env.NODE_ENV === "sandbox",
    };
  },
  mixins: [mixin],
  created() {
    this.init();
  },
  methods: {
    init() {
    },
    handleOutsideClick() {
      this.isUserMenuShow = false;
    },
    handleUserClick() {
      this.isUserMenuShow = !this.isUserMenuShow;
    },
    clearSession() {
      this.$emit("clear-session");
    },
    linkTo(type) {
      let url = "";
      if (type === "book") {
        url = `https://linkis.apache.org/`;
      } else if (type === "github") {
        url = `https://linkis.apache.org/`;
      } else if (type === "freedback") {
        url = "https://linkis.apache.org/";
        if (localStorage.getItem("locale") === "en") {
          url = "https://linkis.apache.org/";
        }
      }
      util.windowOpen(url);
    },
    goConsole(){
      this.$router.push({path: '/console',query: Object.assign({}, this.$route.query)});
    },
    goRolesPath() {
      // 根据接口getWorkspaceBaseInfo渲染跳转不同路径
      this.$router.push({path: this.homeRoles.path, query: Object.assign({}, this.$route.query)});
    },
  }
};
</script>
<style lang="scss" scoped src="./index.scss"></style>
