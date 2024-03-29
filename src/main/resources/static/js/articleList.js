
document.addEventListener("DOMContentLoaded", function() {
    // 게시글 데이터
    var posts = [
        { id: 1, title: "첫 번째 게시글", content: "1 번째 게시글 내용입니다.", nickname: "user1", created_at: "2024-03-25", updated_at: "2024-03-25" },
        { id: 2, title: "두 번째 게시글", content: "2 번째 게시글 내용입니다.", nickname: "user2", created_at: "2024-03-24", updated_at: "2024-03-25" },
        { id: 3, title: "세 번째 게시글", content: "3 번째 게시글 내용입니다.", nickname: "user3", created_at: "2024-03-23", updated_at: "2024-03-25" },
        { id: 4, title: "네 번째 게시글", content: "4 번째 게시글 내용입니다.", nickname: "user4", created_at: "2024-03-22", updated_at: "2024-03-25" },
        { id: 5, title: "다섯 번째 게시글", content: "5 번째 게시글 내용입니다.", nickname: "user5", created_at: "2024-03-21", updated_at: "2024-03-25" },
        { id: 6, title: "여섯 번째 게시글", content: "6 번째 게시글 내용입니다.", nickname: "user6", created_at: "2024-03-16", updated_at: "2024-03-25" },
        { id: 7, title: "일곱 번째 게시글", content: "7 번째 게시글 내용입니다.", nickname: "user7", created_at: "2024-03-19", updated_at: "2024-03-25" },
        { id: 8, title: "여덟 번째 게시글", content: "8 번째 게시글 내용입니다.", nickname: "user8", created_at: "2024-03-18", updated_at: "2024-03-25" },
        { id: 9, title: "아홉 번째 게시글", content: "9 번째 게시글 내용입니다.", nickname: "user9", created_at: "2024-03-15", updated_at: "2024-03-25" },
    ];

    var currentPage = 1; // 현재 페이지

    function searchContent() {
        var searchInput = document.getElementById("searchInput").value.toLowerCase(); // 입력된 검색어를 소문자로 변환하여 가져옴
        var filteredPosts = posts.filter(function(post) {
            return post.content.toLowerCase().includes(searchInput); // 검색어가 내용에 포함되어 있는 게시글 필터링
        });
        displayFilteredPosts(filteredPosts); // 필터링된 게시글을 표시하는 함수 호출
    }

    function displayFilteredPosts(filteredPosts) {
        var container = document.getElementById("posts-container");
        container.innerHTML = ""; // 기존 게시글들 초기화

        filteredPosts.forEach(function(post) {
            var row = container.insertRow();
            var titleCell = row.insertCell(0);
            var contentCell = row.insertCell(1);
            var nicknameCell = row.insertCell(2);
            var createdAtCell = row.insertCell(3);
            var updatedAtCell = row.insertCell(4);
            titleCell.textContent = post.title;
            contentCell.textContent = post.content;
            nicknameCell.textContent = post.nickname;
            createdAtCell.textContent = post.created_at;
            updatedAtCell.textContent = post.updated_at;

            // 각 게시글의 제목을 클릭하면 해당 게시글의 내용을 표시하는 페이지로 이동
            titleCell.classList.add("post-title");
            titleCell.addEventListener("click", function() {
                window.location.href = "post.html?id=" + post.id;
            });

            // 각 게시글의 내용을 클릭하면 해당 게시글의 내용을 표시하는 페이지로 이동
            contentCell.classList.add("post-content");
            contentCell.addEventListener("click", function() {
                window.location.href = "post.html?id=" + post.id;
            });
        });
    }

    // 게시글 표시 함수
    function displayPosts(page) {
        var container = document.getElementById("posts-container");
        container.innerHTML = ""; // 기존 게시글들 초기화

        var startIndex = (page - 1) * 7; // 시작 인덱스 계산
        var endIndex = startIndex + 7; // 끝 인덱스 계산
        var currentPagePosts = posts.slice(startIndex, endIndex); // 현재 페이지의 게시글들

        currentPagePosts.forEach(function(post) {
            var row = container.insertRow();
            var titleCell = row.insertCell(0);
            var contentCell = row.insertCell(1);
            var nicknameCell = row.insertCell(2);
            var createdAtCell = row.insertCell(3);
            var updatedAtCell = row.insertCell(4);
            titleCell.textContent = post.title;
            contentCell.textContent = post.content;
            nicknameCell.textContent = post.nickname;
            createdAtCell.textContent = post.created_at;
            updatedAtCell.textContent = post.updated_at;

            // 각 게시글의 제목을 클릭하면 해당 게시글의 내용을 표시하는 페이지로 이동
            titleCell.classList.add("post-title");
            titleCell.addEventListener("click", function() {
                window.location.href = "post.html?id=" + post.id;
            });

            // 각 게시글의 내용을 클릭하면 해당 게시글의 내용을 표시하는 페이지로 이동
            contentCell.classList.add("post-content");
            contentCell.addEventListener("click", function() {
                window.location.href = "post.html?id=" + post.id;
            });
        });
    }

    // 페이지네이션 표시 함수
    function displayPagination() {
        var totalPages = Math.ceil(posts.length / 7); // 전체 페이지 수 계산
        var paginationContainer = document.getElementById("pagination");
        paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

        for (var i = 1; i <= totalPages; i++) {
            var pageLink = document.createElement("a");
            pageLink.textContent = i;
            pageLink.href = "#";
            pageLink.classList.add("pagination-link");
            pageLink.addEventListener("click", function(event) {
                event.preventDefault();
                currentPage = parseInt(this.textContent); // 현재 페이지 업데이트
                displayPosts(currentPage); // 해당 페이지의 게시글 표시
            });
            paginationContainer.appendChild(pageLink);
        }
    }

    // 페이지 로드 시 초기 게시글과 페이지네이션 표시
    displayPosts(currentPage);
    displayPagination();
});